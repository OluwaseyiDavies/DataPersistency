package nl.hu.dp.P4.domain;

import java.util.Date;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kaart_nummer")
    private int kaart_nummer;

    @Column(name = "geldig_tot")
    private Date geldig_tot;

    @Column(name = "klasse")
    private int klasse;

    @Column(name = "saldo")
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    public OVChipkaart() {}



}

























