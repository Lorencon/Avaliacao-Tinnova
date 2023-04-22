export class Veiculo {
    id: number
    veiculo: string
    marca: string
    ano: number
    descricao: string
    vendido: boolean
    created: any
    updated: any
    cor: string

    constructor() {
        this.id = 0;
        this.veiculo = '';
        this.marca = '';
        this.ano = 0;
        this.descricao = '';
        this.vendido = false;
        this.created = null;
        this.updated = null;
        this.cor = '';
    }
}
