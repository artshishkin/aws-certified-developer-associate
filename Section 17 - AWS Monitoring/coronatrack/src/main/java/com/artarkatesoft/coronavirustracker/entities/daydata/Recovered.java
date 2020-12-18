package com.artarkatesoft.coronavirustracker.entities.daydata;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "recovered", uniqueConstraints = {@UniqueConstraint(columnNames = {"date","location_id"})})
public class Recovered extends BaseDayDataEntity{
}
