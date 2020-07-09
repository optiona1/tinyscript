const TokenType = require('./TokenType')
class Token {
    constructor(type, value) {
        this._type = type
        this._value = value
    }

    getType() {
        return this._type
    }

    isVariable() {
        return this._type == TokenType.VARIABLE
    }

    isScalar() {
        return this._type == TokenType.BOOLEAN ||
            this._type == TokenType.FLOAD ||
            this._type == TokenType.INTEGER ||
            this._type == TokenType.STRING
    }

    toString() {
        return `type ${this._type}, value ${this._value}`
    }
}

module.exports = Token