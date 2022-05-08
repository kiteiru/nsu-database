package com.kiteiru.construction.org.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "path_type")
public class PathType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "lane_num", nullable = false)
    private Integer laneNum;

    @Column(name = "width", nullable = false)
    private Double width;

    @Column(name = "length", nullable = false)
    private Double length;

}