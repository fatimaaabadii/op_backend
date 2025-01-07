package ma.entraide.ramadan.service;

import ma.entraide.ramadan.entity.Stock;
import ma.entraide.ramadan.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }

    public List<Stock> getStocksByDelegation(Long delegationId) {
        return stockRepository.findByDelegationDelegationId(delegationId);
    }

   /* public List<Stock> getStocksByProduit(Long produitId) {
        return stockRepository.findByProduit_Id(produitId);
    }*/

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStock(Long id, Stock stockDetails) {
        return stockRepository.findById(id).map(stock -> {
            stock.setDelegation(stockDetails.getDelegation());
            stock.setProduit(stockDetails.getProduit());
            stock.setQuantiteMax(stockDetails.getQuantiteMax());
            return stockRepository.save(stock);
        }).orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));
    }
    
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    // Add custom service methods if needed
}