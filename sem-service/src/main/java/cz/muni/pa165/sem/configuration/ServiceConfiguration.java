package cz.muni.pa165.sem.configuration;

import cz.muni.pa165.sem.dto.*;
import cz.muni.pa165.sem.entity.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Matej Majdis
 * @author Kamil Triscik
 */
@Configuration
@Import({DBConfig.class})
@ComponentScan(basePackages = "cz.muni.pa165.sem")
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerSportsmanConfig());
        dozer.addMapping(new DozerEventConfig());
        dozer.addMapping(new DozerSportConfig());
        dozer.addMapping(new DozerResultConfig());
        dozer.addMapping(new DozerInvitationConfig());
        return dozer;
    }

    private class DozerSportsmanConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Sportsman.class, SportsmanDTO.class);
        }
    }

    private class DozerEventConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Event.class, EventDTO.class);
        }
    }

    private class DozerSportConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Sport.class, SportDTO.class);
        }
    }

    private class DozerResultConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Result.class, ResultDTO.class);
        }
    }

    private class DozerInvitationConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Invitation.class, InvitationDTO.class);
        }
    }
}
