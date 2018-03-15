import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS,
include=JsonTypeInfo.As.PROPERTY, property="@class")
public class Question {
    private final int question;

    @JsonCreator
    public Question(
            @JsonProperty("question")
            int question
    ) {
        this.question = question;
    }

    @JsonProperty("question")
    public int getQuestion() {
        return question;
    }
}
