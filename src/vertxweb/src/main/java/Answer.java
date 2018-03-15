import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS,
include=JsonTypeInfo.As.PROPERTY, property="@class")
public class Answer {
    private final String id;
    private final int question;
    private final List<Integer> answers;

    @JsonCreator
    public Answer(
            @JsonProperty("id")
            String id,
            @JsonProperty("question")
            int question,
            @JsonProperty("answers")
            List<Integer> answers
    ) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("question")
    public int getQuestion() {
        return question;
    }

    @JsonProperty("answers")
    public List<Integer> getAnswers() {
        return answers;
    }
}
