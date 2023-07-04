export const ProductRemoval = () => {
    const deleteProduct = () => {}

    return (
        <div className={"product-container"}>
            <div className={"row"}>
                <span className={"column-field-name"}>id:</span>
                <input className={"column-field-value"} type={"number"} min={1} defaultValue={1}/>
            </div>
            <button onClick={deleteProduct}>Удалить</button>
        </div>
    )
}
