export const Payment = () => {
    const pay = () => {}

    return (
        <div className={"product-container"}>
            <div className={"row"}>
                <span className={"column-field-name"}>id:</span>
                <input className={"column-field-value"} type={"number"} min={1} defaultValue={1}/>
            </div>
            <div className={"row"}>
                <span className={"column-field-name"}>Номер банк. карты:</span>
                <input className={"column-field-value"} type={"text"}/>
            </div>
            <button onClick={pay}>Оплатить</button>
        </div>
    )
}
