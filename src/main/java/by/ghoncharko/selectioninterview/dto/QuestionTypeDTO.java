package by.ghoncharko.selectioninterview.dto;

import by.ghoncharko.selectioninterview.validation.group.UpdateValidationGroup;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestionTypeDTO {
    @NotNull(groups = UpdateValidationGroup.class,message = "id must be not null")
    @Positive(groups = UpdateValidationGroup.class, message = "id must be more than zero")
    private BigInteger id;
    @NotEmpty(message = "question type name must be not empty")
    @Size(max = 1000, message = "question type name max size 1000")
    private String questionTypeName;

    private Timestamp dateCreated;

    private Timestamp lastDateUpdated;

    private boolean deleted;
    private List<QuestionDTOWithoutAnswersAndType> questions = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionTypeDTO that = (QuestionTypeDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

