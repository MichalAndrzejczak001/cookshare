package spring.project.cookshare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;

    private String fileType;

    @Lob
    private Blob image;

    private String downloadUrl;

    @OneToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
