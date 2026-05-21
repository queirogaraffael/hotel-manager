package com.example.gerenciador.hotel.architecture;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.equivalentTo;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;

@Tag("arch")
@AnalyzeClasses(packages = "com.example.gerenciador.hotel", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest {

    @ArchTest
    static final ArchRule domain_should_not_depend_on_infrastructure = noClasses()
            .that().resideInAPackage("..domain..")
            .should().dependOnClassesThat().resideInAPackage("..infrastructure..")
            .as("A camada de domínio não deve depender da camada de infraestrutura.");

    @ArchTest
    static final ArchRule domain_should_not_depend_on_spring_except_transactional = noClasses()
            .that().resideInAPackage("..domain..")
            .should().dependOnClassesThat(
                    resideInAPackage("org.springframework..")
                            .and(DescribedPredicate.not(equivalentTo(org.springframework.transaction.annotation.Transactional.class)))
            )
            .as("A camada de domínio não deve depender do framework Spring, exceto pela anotação @Transactional.");

    @ArchTest
    static final ArchRule controllers_must_be_suffixed_and_annotated = classes()
            .that().resideInAPackage("..infrastructure.adapter.in.web")
            .and().haveSimpleNameEndingWith("Controller")
            .should().beAnnotatedWith(RestController.class)
            .as("Controllers da camada de entrada (web) devem ser anotados com @RestController.");

    @ArchTest
    static final ArchRule controllers_should_reside_in_web_package = classes()
            .that().areAnnotatedWith(RestController.class)
            .should().resideInAPackage("..infrastructure.adapter.in.web")
            .andShould().haveSimpleNameEndingWith("Controller")
            .as("Todos os RestControllers devem residir no pacote web e possuir o sufixo 'Controller'.");

    @ArchTest
    static final ArchRule usecases_must_be_suffixed = classes()
            .that().resideInAPackage("..domain.usecase..")
            .should().haveSimpleNameEndingWith("UseCase")
            .as("UseCases no domínio devem possuir o sufixo 'UseCase'.");

    @ArchTest
    static final ArchRule usecases_should_implement_input_ports = classes()
            .that().resideInAPackage("..domain.usecase..")
            .should().implement(resideInAPackage("..domain.port.in.."))
            .as("Todos os UseCases devem implementar uma interface do pacote domain.port.in.");

    @ArchTest
    static final ArchRule ports_must_be_interfaces = classes()
            .that().resideInAPackage("..domain.port..")
            .should().beInterfaces()
            .as("Ports de entrada e saída devem ser interfaces.");

    @ArchTest
    static final ArchRule no_system_out_println = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    static final ArchRule no_generic_exceptions = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    static final ArchRule no_field_injection = noFields()
            .that().areDeclaredInClassesThat().haveSimpleNameNotEndingWith("Impl")
            .should().beAnnotatedWith(Autowired.class)
            .as("Nenhuma classe deve usar @Autowired em propriedades (Field Injection), exceto os Mappers gerados automaticamente. Use injeção por construtor.");
}
