package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Tag")
@Table(name = "Tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Tag_Generator")
    @SequenceGenerator(name = "Tag_Generator", sequenceName = "Tag_Sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "Tag_Id")
    private Long id;

    @Column(name = "Tag_Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "Pet_Id")
    private Pet pet;
}

/*
Tag{
    id	integer($int64)
    name	string
}
*/
