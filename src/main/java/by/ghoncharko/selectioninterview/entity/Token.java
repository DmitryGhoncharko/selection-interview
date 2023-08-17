package by.ghoncharko.selectioninterview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "_token_blacklist")
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id",nullable = false)
    private BigInteger id;
    @Column(name = "token_value", nullable = false)
    private String tokenValue;
    @Column(name = "expire_date",nullable = false)
    private Timestamp expireDate;
}
