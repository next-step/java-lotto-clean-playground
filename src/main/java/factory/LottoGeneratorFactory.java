package factory;

import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import domain.generator.ManualLottoGenerator;

import java.util.List;

public class LottoGeneratorFactory {

    private final LottoGenerator autoGenerator;

    public LottoGeneratorFactory() {
        this.autoGenerator = new AutoLottoGenerator();
    }

    public LottoGenerator getAutoGenerator() {
        return autoGenerator;
    }
}
