package br.com.infotera.santander.model.RQRS;

import br.com.infotera.santander.model.AtxResponse;
import br.com.infotera.santander.model.SimulationResponse;

public class SimulacaoRS {

    private AtxResponse atxResponse;

    private SimulationResponse simulationResponse;

    public SimulacaoRS() {
    }

    public SimulacaoRS(AtxResponse atxResponse, SimulationResponse simulationResponse) {
        this.atxResponse = atxResponse;
        this.simulationResponse = simulationResponse;
    }

    public AtxResponse getAtxResponse() {
        return atxResponse;
    }

    public void setAtxResponse(AtxResponse atxResponse) {
        this.atxResponse = atxResponse;
    }

    public SimulationResponse getSimulationResponse() {
        return simulationResponse;
    }

    public void setSimulationResponse(SimulationResponse simulationResponse) {
        this.simulationResponse = simulationResponse;
    }
}
