package utils.builders;

import models.picking.Picker;
import models.picking.TournamentPicker;
import options.AlgorithmProperties;
import utils.ExceptionUtils;

public class PickerBuilder {
    public Picker build(AlgorithmProperties algorithmProperties) {
        switch (algorithmProperties.getPickingType()) {
            case TOURNAMENT:
                return new TournamentPicker(algorithmProperties.getPickingTournamentParameter());
            default:
                throw ExceptionUtils.unknownEnumItem(algorithmProperties.getPickingType());
        }
    }
}
