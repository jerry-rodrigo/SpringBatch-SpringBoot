package com.batch.SpringBatchApplication.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.batch.SpringBatchApplication.config.ClienteRowMapper;
import com.batch.SpringBatchApplication.dto.ClienteDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

@RunWith(MockitoJUnitRunner.class)
public class ClienteRowMapperTest {

    @Mock
    private RowSet rowSet;

    private ClienteRowMapper clienteRowMapper;

    @Before
    public void setup() {
        clienteRowMapper = new ClienteRowMapper();
    }

    @Test
    public void testMapRow() {
        // Configurar el comportamiento simulado del RowSet
        when(rowSet.getCurrentRow()).thenReturn(new String[]{"1", "Jerry", "Doe", "123456789", "john.doe@example.com"});

        // Ejecutar el método a probar
        ClienteDTO clienteDTO = clienteRowMapper.mapRow(rowSet);

        // Verificar que el mapeo se realizó correctamente
        //assertEquals(1L, clienteDTO.getId().longValue());
        assertEquals("Jerry", clienteDTO.getNombres());
        assertEquals("Doe", clienteDTO.getApellidos());
        assertEquals("123456789", clienteDTO.getDni());
        assertEquals("john.doe@example.com", clienteDTO.getEmail());
    }

    @Test
    public void testMapRowWithEmptyRow() {
        // Configurar el comportamiento simulado del RowSet para una fila vacía
        when(rowSet.getCurrentRow()).thenReturn(new String[]{});

        // Ejecutar el método a probar
        ClienteDTO clienteDTO = clienteRowMapper.mapRow(rowSet);

        // Verificar que se devuelva un objeto ClienteDTO vacío
        //assertEquals(null, clienteDTO.getId());
        assertEquals(null, clienteDTO.getNombres());
        assertEquals(null, clienteDTO.getApellidos());
        assertEquals(null, clienteDTO.getDni());
        assertEquals(null, clienteDTO.getEmail());
    }

    // Otras pruebas para casos de borde y casos de error
}

