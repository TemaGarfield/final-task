package by.kotik.please_work_jwt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "collections")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "collection",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Setter(AccessLevel.PRIVATE)
    private List<Item> items;

    public Collection() { }

    public Collection(String name, String description, Topic topic, User user) {
        this.name = name;
        this.description = description;
        this.topic = topic;
        this.user = user;
    }

    public Collection(Long id, String name, String description, Topic topic, User user, List<Item> items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topic = topic;
        this.user = user;
        this.items = items;
    }

    public Collection(Long id, String name, String description, Topic topic, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topic = topic;
        this.user = user;
    }
}
