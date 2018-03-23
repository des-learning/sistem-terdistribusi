import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class XKCDVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer http = vertx.createHttpServer();
        WebClient webClient = WebClient.create(vertx);
        String url = "https://xkcd.com/";

        Router router = Router.router(vertx);
        router.get("/").handler(context -> {
            HttpServerResponse response = context.response();
            // baca halaman dari parameter query, jika nomor tidak ditemukan atau bukan angka, kembalikan nilai default
            // 0
            Integer page = parseInt(context.queryParam("page"))
                    .orElse(0);

            // hasil output berupa streaming
            response.setChunked(true);

            response.write("<html><head><title>XKCD</title></head><body>");

            // daftar nomor halaman
            List<Integer> pages = range(5*page+1, 5*page+5);

            // kumpulkan seluruh future tasks ke dalam list
            // pada contoh ini, aplikasi akan melakukan request sebanyak x ke web server (pada contoh ini 5)
            List<Future> requests = pages.stream()
                    .map(n -> loadXKCD(webClient, url + n + "/info.0.json", response))
                    .collect(Collectors.toList());

            // apabila seluruh future tasks sudah selesai, lakukan proses berikutnya
            CompositeFuture.all(requests).setHandler(result -> {
                // handler yang melanjutkan proses setelah seluruh future tasks yang melakukan request ke web selesai
                // dan sukses
                if (result.succeeded()) {
                    response.write("<div>");
                    if (page > 0)
                        response.write("<a href=\"/?page=" + (page-1) + "\">Prev</a>");
                    response.write("<a href=\"/?page=" + (page+1) + "\">Next</a>");
                    response.write("</div>");
                } else {
                    result.cause().printStackTrace();
                    response.write("<div>Some request cannot be completed");
                }
                response.write("</body>");
                response.end();
            });

        });

        http.requestHandler(router::accept);
        http.listen(8080);
    }

    // fungsi optional di java adalah untuk hasil operasi yang mengembalikan opsi hasil, pada contoh ini mengembalikan
    // Optional Integer apabila proses parsing berhasil, empty apabila proses parsing gagal
    private Optional<Integer> parseInt(List<String> s) {
        try {
            return Optional.of(Integer.parseInt(s.get(0)));
            // menggunakan fitur java 7 untuk exception yang memiliki parent yang sama
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    // future mengembalikan hasil eksekusi asynchronous
    private Future loadXKCD(WebClient client, String url, HttpServerResponse response) {
        Future future = Future.future();
        // request ke web server dilakukan secara asynchronous
        client.getAbs(url).ssl(true).send(result -> {
            // handler setelah request selesai
            if (result.succeeded()) {
                JsonObject json = result.result().bodyAsJsonObject();
                response.write("<div><img src=\"" + json.getString("img") + "\"><p>" + json.getString("title") + "</p></div>" );
            } else {
                response.write("<div><p>Resource cannot retrieved</div>");
            }
            // notifikasi proses asynchronous selesai
            future.complete();
        });
        // kembalikan task future untuk diproses oleh client
        return future;
    }

    private List<Integer> range(int from, int to) {
        List<Integer> result = new ArrayList<>();
        for (int i = from; i <= to; i++) result.add(i);
        return result;
    }

}
