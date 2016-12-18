package cz.muni.pa165.sem.controller.rest.mixins;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * We do not provide password(hash) on REST request for sportsman.
 *
 * @author Kamil Triscik
 */
@JsonIgnoreProperties({"password"})
public class SportsmanDTOMixIn {
}

