package by.ghoncharko.selectioninterview.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class QuestionGroupDTO {

    private BigInteger id;

    private String groupName;

    private Timestamp dateCreated;

    private Timestamp lastDateUpdated;

    private boolean deleted;
}
