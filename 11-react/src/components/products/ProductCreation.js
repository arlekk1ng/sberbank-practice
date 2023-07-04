export const ProductCreation = () => {
    const createProduct = () => {}

    return (
        <div className={"product-container"}>
            <div className={"row"}>
                <span className={"column-field-name"}>Наименование:</span>
                <input className={"column-field-value"} type={"text"}/>
            </div>
            <div className={"row"}>
                <span className={"column-field-name"}>Цена:</span>
                <input className={"column-field-value"} type={"number"} min={0} defaultValue={0}/>
            </div>
            <div className={"row"}>
                <span className={"column-field-name"}>Количество:</span>
                <input className={"column-field-value"} type={"number"} min={0} defaultValue={0}/>
            </div>
            <button onClick={createProduct}>Создать</button>
        </div>
    )
}
