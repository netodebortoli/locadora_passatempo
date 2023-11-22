package com.locadora.backendlocadora.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Dependente extends Cliente<Long, Dependente> {

}
