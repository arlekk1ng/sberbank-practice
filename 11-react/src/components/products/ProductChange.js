export const ProductChange = () => {
    const changeProduct = () => {}

    return (
        <div className={"product-container"}>
            <div className={"row"}>
                <span className={"column-field-name"}>id:</span>
                <input className={"column-field-value"} type={"number"} min={1} defaultValue={1}/>
            </div>
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
            <button onClick={changeProduct}>Изменить</button>
        </div>
    )
}
