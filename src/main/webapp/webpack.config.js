const SpeedMeasurePlugin = require("speed-measure-webpack-plugin");
var HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');
const smp = new SpeedMeasurePlugin();

const webpackConfig = smp.wrap({
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
                use: ['css-loader'],
            },
            {
                test: /\.s[ac]ss$/i,
                use: [
                    // Translates CSS into CommonJS
                    'css-loader',
                    // Compiles Sass to CSS
                    'sass-loader',
                ],
            },
        ]
    },
    plugins: [new HtmlWebpackPlugin({
        template: './src/index.html'
    })],
    devServer: {
        historyApiFallback: true
    }
});

module.exports = webpackConfig