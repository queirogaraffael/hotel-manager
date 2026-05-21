package com.example.gerenciador.hotel.infrastructure.adapter.in.web;

import com.example.gerenciador.hotel.domain.port.in.endereco.ModificarEnderecoInputPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enderecos")
@Tag(name = "Endereços", description = "Operações comuns de endereço para todas as entidades que se relacionam com ele.")
public class EnderecoController {

    @Autowired
    private ModificarEnderecoInputPort modificarEnderecoInputPort;
}
