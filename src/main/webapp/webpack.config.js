var HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');

module.exports = {
    mode: 'development',
    resolve: {
        extensions: ['.ts', '.tsx', '.js']
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                include: path.resolve(__dirname, 'src'),
                exclude: /node_modules/,
            },
            {
                test: /\.css$/i,
                include: path.resolve(__dirname, 'src'),
                use: ['css-loader'],
            },
        ]
    },
    plugins: [new HtmlWebpackPlugin({
        template: './src/index.html'
    })],
    devServer: {
        historyApiFallback: true
    }
}