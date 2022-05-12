package client.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import orm.annotation.Column;
import orm.annotation.Entity;
import orm.annotation.Id;
import orm.annotation.Table;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name="Zoo")
@NoArgsConstructor
@RequiredArgsConstructor
public class Zoo {
    @Id
    Long id;

    @Column(name="title")
    @NonNull
    String name;

    @OneToMany(mappedBy="zoo")
    List<Animal> animals = new ArrayList<>();
}
