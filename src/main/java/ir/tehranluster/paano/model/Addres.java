package ir.tehranluster.paano.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Table(name="addres" , schema = "public")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Addres {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator ="addres_seq" )
    @SequenceGenerator(name = "addres_seq", sequenceName = "addres_seq", allocationSize = 1)
    @Column(name="ID")
    private Long id;

    @Column(name = "phone" , length = 100)
    private String phone;

    @Column(name = "addr" , length = 200)
    private String addr;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "addres" , fetch = FetchType.LAZY)
    private UserDetail userDetail;


}
