package by.ghoncharko.selectioninterview.dto;

import by.ghoncharko.selectioninterview.validation.group.UpdateValidationGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class QuestionGroupDTOForCreateOrUpdate {
    @NotNull(groups = UpdateValidationGroup.class, message = "id must be not null")
    @Positive(groups = UpdateValidationGroup.class, message = "id must be more than zero")
    private BigInteger id;
    @NotEmpty(message = "question group name must be not empty")
    @Size(max = 1000, message = "question group name max size 1000")
    @NotEmpty(message = "question group name be not empty")
    private String groupName;
    private boolean deleted;
}
