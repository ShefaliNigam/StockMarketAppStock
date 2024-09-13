package stockMarketChartingApp.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stockMarketChartingApp.Entity.StockEntity;
import stockMarketChartingApp.Service.StockService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    @PostMapping("/add")
    public ResponseEntity<StockEntity> addStock(@RequestBody StockEntity stockEntity) {
    	
        logger.info("Received request to add new stock: {} with symbol: {}", stockEntity.getName(), stockEntity.getSymbol());

        StockEntity stockData = stockService.addStock(stockEntity);

        logger.info("Successfully saved stock with ID: {} and symbol: {}", stockData.getId(), stockData.getSymbol());
    	
        return ResponseEntity.ok(stockData);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<StockEntity> updateStock(@PathVariable Long id, @RequestBody StockEntity stockEntity) {
    	 logger.info("Received request to update stock with id: {}", id);
        return ResponseEntity.ok(stockService.updateStock(id, stockEntity));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
    	logger.info("Received request to delete stock with id: {}", id);
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<StockEntity> getStock(@PathVariable Long id) {
    	
    	StockEntity stock = stockService.getStock(id);
    	logger.info("Successfully fetched stock with id: {} and symbol: {}", stock.getId(), stock.getSymbol());

      //  logger.info("Received request to fetch stock with id: {}", id);
        return ResponseEntity.ok(stock);
    }
}
