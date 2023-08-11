package by.ghoncharko.selectioninterview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "answer")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private BigInteger id;
    @Column(name = "answer_body", length = 3000)
    private String answerBody;
    @Column(name = "answer_correct", nullable = false)
    private Boolean answerCorrect;
    @Column(name = "date_created", nullable = false)
    private Timestamp dateCreated;
    @Column(name = "last_date_updated", nullable = false)
    private Timestamp lastDateUpdated;
    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @ToString.Exclude
    private Question question;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer()
                .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Answer answer = (Answer) o;
        return id != null && Objects.equals(id, answer.id);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass()
                .hashCode() : getClass().hashCode();
    }
}
