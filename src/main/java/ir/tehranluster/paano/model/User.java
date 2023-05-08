package ir.tehranluster.paano.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import ir.tehranluster.paano.utils.Provides;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Entity
@Table(name="user" , schema = "public")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @Column(name="ID")
    private Long id;
    @Column(name = "email" , length = 100 , unique = true , nullable = false)
    private String email;
    @Column(name = "PASSWORD" , length = 200 , nullable = false)
    private String password;
    @Column(name = "provider" , length = 50)
    @Enumerated(EnumType.STRING)
    private Provides provider;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updateDate;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user" , fetch = FetchType.LAZY)
    private UserDetail userDetail;

    @PrePersist
    protected void onCreateDate(){
        this.createDate = new Date();
        log.info( "created :" + this.toString());
    }
    @PreUpdate
    protected void onUpdateDate(){
        this.createDate = new Date();
        log.info( "updated :" + this.toString());
    }
    @PreRemove
    protected void onRemove(){
        log.info("removed :" + this.toString());
    }
}
