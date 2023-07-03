export const ProductRemoval = () => {
    const handlerClick = () => {

    }

    return (
        <div>
            <div>id: <input type={"number"} min={1} defaultValue={1}/></div>
            <button onClick={handlerClick}>Удалить</button>
        </div>
    )
}
