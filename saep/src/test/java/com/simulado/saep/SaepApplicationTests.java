package com.simulado.saep;


import com.simulado.saep.dtos.ProdutoDto;
import com.simulado.saep.entities.ProdutoEntity;
import com.simulado.saep.services.MovimentacaoEstoqueService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


class SaepApplicationTests {

	@Nested
	class TestandoAlmoxarifado {

		@Test
		@ParameterizedTest(name = "Estoque {0} é negativo")
		@ValueSource(ints = {-1, -5, -25, -1000})
		void verificaExceptionQuantidadeNegativa(int estoque) {

			ProdutoDto produtoDto = new ProdutoDto();

			IllegalArgumentException excecao = assertThrows(
					IllegalArgumentException.class,
					() -> produtoDto.setQuantidadeEstoque(estoque)
			);

			assertEquals(
					"Quantidade não pode ser negativa.",
					excecao.getMessage()
			);

		}
		@ParameterizedTest(name = "Estoque {0} é negativo")
		@ValueSource(ints = {-1, -5, -25, -1000})
		void verificaQuantidadeCerta(int estoque) {

		ProdutoDto produtoDto = new ProdutoDto();

			int resultado = produtoDto.setQuantidadeEstoque(estoque);

			assertTrue(resultado);

		}
	}
}

