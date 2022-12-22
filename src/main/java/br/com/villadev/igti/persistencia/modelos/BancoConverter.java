package br.com.villadev.igti.persistencia.modelos;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BancoConverter implements AttributeConverter<Banco, String> {

	@Override
	public String convertToDatabaseColumn(Banco banco) {
		return banco == null ? null : banco.getNome();
	}

	@Override
	public Banco convertToEntityAttribute(String nomeBanco) {
		if (nomeBanco == null) {
            return null;
        }
		return Stream.of(Banco.values())
		          .filter(c -> c.getNome().equals(nomeBanco))
		          .findFirst()
		          .orElseThrow(IllegalArgumentException::new);
	}

}
