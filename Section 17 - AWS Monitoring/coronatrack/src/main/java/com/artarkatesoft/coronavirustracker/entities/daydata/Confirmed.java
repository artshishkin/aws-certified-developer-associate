package com.artarkatesoft.coronavirustracker.entities.daydata;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "confirmed", uniqueConstraints = {@UniqueConstraint(columnNames = {"date","location_id"})})
public class Confirmed extends BaseDayDataEntity {
}
