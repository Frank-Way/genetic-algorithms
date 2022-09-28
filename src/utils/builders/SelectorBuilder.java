package utils.builders;

import models.selection.EliteSelector;
import models.selection.Selector;
import options.AlgorithmProperties;
import utils.ExceptionUtils;

public class SelectorBuilder {
    public Selector build(AlgorithmProperties algorithmProperties) {
        switch (algorithmProperties.getSelectionType()) {
            case ELITE:
                return new EliteSelector(algorithmProperties.getSelectionElitePart());
            default:
                throw ExceptionUtils.unknownEnumItem(algorithmProperties.getSelectionType());
        }
    }
}
