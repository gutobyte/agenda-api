package com.br.backend.agenda.agendaapi.model.controller;


import com.br.backend.agenda.agendaapi.model.entity.Contato;
import com.br.backend.agenda.agendaapi.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agenda")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ContatoController {

    private final ContatoRepository contatoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato salvar(@RequestBody Contato contato){

        return contatoRepository.save(contato);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        contatoRepository.deleteById(id);
    }

    @GetMapping
    public List<Contato> listar(){

        return contatoRepository.findAll();
    }

    //atualização parcial
    @PatchMapping("{id}/favorito")
    public void favorite(@PathVariable Integer id){
        Optional<Contato> contato = contatoRepository.findById(id);
        contato.ifPresent( c -> {
            boolean favorito = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!favorito);
            contatoRepository.save(c);
        });

    }
    ///api/contatos/1/fotos
    @PutMapping("{id}/foto")
    public byte[] adicionarFoto(@PathVariable Integer id, @RequestParam("foto") Part arquivo){

        Optional<Contato> contato = contatoRepository.findById(id);
        return contato.map( c -> {
            try{
                InputStream is = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(is, bytes); //pega o input e joga dentro do array bytes
                c.setFoto(bytes);
                contatoRepository.save(c);
                is.close();
                return bytes;

            }catch (IOException e){
                return null;
            }
        }).orElse(null);

    }
}
