```mermaid
sequenceDiagram
    main ->>+ machine: new Machine()
    machine ->>+ tank: new FuelTank()
    tank ->>- machine: tank
    machine ->> tank: fill(40)
    machine ->>+ engine: new Engine()
    engine ->>- machine: engine
    machine ->>- main: machine
    main ->> machine: drive()
    machine ->> engine: start()
    engine ->> tank: consume(5)
    machine ->>+ engine: isRunning()
    engine ->>-machine: true
    machine ->> engine: useEnergy()
    engine ->> tank: consume(10)
