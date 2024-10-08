package med.voll.api.domain.consulta.validacoes.cancelamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {
    
    @Autowired
    private ConsultaRepository repository;


    @SuppressWarnings("null")
    public void validar(DadosCancelamentoConsulta dados) {
        
        var consulta = repository.getReferenceById(dados.idConsulta());
        
        var agora = LocalDateTime.now();

        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24 ) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedênca de 24 horas");
        }

    } 

}
