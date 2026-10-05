package com.thomasmylonas.petstore_api_app.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Tag")
@Table(name = "Tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Tags_Id_Seq_Generator")
    @SequenceGenerator(name = "Tags_Id_Seq_Generator", sequenceName = "Tags_Id_Seq", initialValue = 1, allocationSize = 1)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "Pet_Id", referencedColumnName = "Id")
    private Pet pet;
}

/*
Swagger model:
------------------
Tag{
    id	    integer($int64)
    name	string
}

"tag": {
      "id": 0,
      "name": "string"
}
*/
