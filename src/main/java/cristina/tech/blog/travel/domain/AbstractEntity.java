package cristina.tech.blog.travel.domain;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;


@MappedSuperclass
@JsonIgnoreProperties({"createdAt", "modifiedAt"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1126074635410771212L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonTypeId
    protected Integer id;

    @Column(name = "created_at")
    @CreatedDate
    protected LocalDateTime createdAt;

    @Column(name = "modified_at")
    @LastModifiedDate
    protected LocalDateTime modifiedAt;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();

        if (createdAt == null) {
            createdAt = now;
        }
        modifiedAt = now;
    }

    protected AbstractEntity() {
    }

    public Integer getId() {
        return id;
    }
}
