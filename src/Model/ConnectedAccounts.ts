class ConnectedAccount {
    private mid: String;

    private constructor(
        mid: String,
    ) {
        this.mid = mid;
    }
    public static setMid(
        mid: String,
    ) {
        return new ConnectedAccount(mid)
    }
}

export { ConnectedAccount }