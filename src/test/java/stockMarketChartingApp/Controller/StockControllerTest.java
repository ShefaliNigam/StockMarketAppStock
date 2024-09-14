package stockMarketChartingApp.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import stockMarketChartingApp.Entity.StockEntity;
import stockMarketChartingApp.Service.StockService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Autowired
    private ObjectMapper objectMapper;

    private StockEntity stockEntity;

    @BeforeEach
    void setUp() {
        stockEntity = new StockEntity();
        stockEntity.setId(1L);
        stockEntity.setName("Tesla");
        stockEntity.setPrice(750.0);
    }

//    @Test
//    public void testAddStock() throws Exception {
//        when(stockService.addStock(any(StockEntity.class))).thenReturn(stockEntity);
//
//        mockMvc.perform(post("/api/stocks/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(stockEntity)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(stockEntity.getId()))
//                .andExpect(jsonPath("$.name").value(stockEntity.getName()))
//                .andExpect(jsonPath("$.price").value(stockEntity.getPrice()))
//                .andDo(print());
//
//        verify(stockService, times(1)).addStock(any(StockEntity.class));
//    }

    @Test
    public void testUpdateStock() throws Exception {
        when(stockService.updateStock(eq(1L), any(StockEntity.class))).thenReturn(stockEntity);

        mockMvc.perform(put("/api/stocks/updateById/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stockEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(stockEntity.getId()))
                .andExpect(jsonPath("$.name").value(stockEntity.getName()))
                .andExpect(jsonPath("$.price").value(stockEntity.getPrice()))
                .andDo(print());

        verify(stockService, times(1)).updateStock(eq(1L), any(StockEntity.class));
    }

    @Test
    public void testDeleteStock() throws Exception {
        doNothing().when(stockService).deleteStock(1L);

        mockMvc.perform(delete("/api/stocks/deleteById/1"))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(stockService, times(1)).deleteStock(1L);
    }

    @Test
    public void testGetStock() throws Exception {
        when(stockService.getStock(1L)).thenReturn(stockEntity);

        mockMvc.perform(get("/api/stocks/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(stockEntity.getId()))
                .andExpect(jsonPath("$.name").value(stockEntity.getName()))
                .andExpect(jsonPath("$.price").value(stockEntity.getPrice()))
                .andDo(print());

        verify(stockService, times(1)).getStock(1L);
    }
}

