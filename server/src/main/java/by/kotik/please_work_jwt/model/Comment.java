package by.kotik.please_work_jwt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonBackReference
    private Item toItem;

    public Comment(String message, User fromUser, Item toItem) {
        this.message = message;
        this.fromUser = fromUser;
        this.toItem = toItem;
    }
}
