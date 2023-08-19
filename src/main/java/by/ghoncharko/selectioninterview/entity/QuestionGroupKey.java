package by.ghoncharko.selectioninterview.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigInteger;

@Embeddable
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@EqualsAndHashCode
public class QuestionGroupKey implements Serializable {
    private BigInteger questionId;
    private BigInteger groupId;
}
