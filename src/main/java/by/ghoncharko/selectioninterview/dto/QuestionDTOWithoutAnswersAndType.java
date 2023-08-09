package by.ghoncharko.selectioninterview.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestionDTOWithoutAnswersAndType {
    @NotNull(message = "id must be not null")
    @Positive(message = "id must be more than zero")
    private BigInteger id;
    @NotEmpty(message = "question body must be not empty")
    @Size(max = 5000,message = "question body max size = 5000")
    private String questionBody;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionDTOWithoutAnswersAndType that = (QuestionDTOWithoutAnswersAndType) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
