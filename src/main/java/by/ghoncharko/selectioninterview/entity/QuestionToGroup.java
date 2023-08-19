package by.ghoncharko.selectioninterview.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "question_to_group")
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class QuestionToGroup {
    @EmbeddedId
    private QuestionGroupKey questionGroupKey;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer()
                .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        QuestionToGroup that = (QuestionToGroup) o;
        return questionGroupKey != null && Objects.equals(questionGroupKey, that.questionGroupKey);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(questionGroupKey);
    }
}
