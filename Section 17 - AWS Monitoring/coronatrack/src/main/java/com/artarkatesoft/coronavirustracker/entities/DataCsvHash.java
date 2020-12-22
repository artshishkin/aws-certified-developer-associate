package com.artarkatesoft.coronavirustracker.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name = "data_csv_hash",
        indexes = {@Index(columnList = "dataUrl")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"dataUrl"})}
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DataCsvHash {
    @Id
    @GeneratedValue
    private Long id;

    private String dataUrl;
    private Integer fileHash;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}
