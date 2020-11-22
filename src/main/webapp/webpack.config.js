var HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack')
// const { ESBuildPlugin, ESBuildMinifyPlugin } = require('esbuild-loader')
const ForkTsCheckerWebpackPlugin = require('fork-ts-checker-webpack-plugin');

module.exports = {
    mode: 'development',
    resolve: {
        extensions: ['.ts', '.tsx', '.js', '.css']
    },
    module: {
        rules: [
            // {
            //     test: /\.tsx?$/,
            //     loader: 'esbuild-loader',
            //     options: {
            //         loader: 'tsx', // Or 'ts' if you don't need tsx
            //         target: 'es2015'
            //     }
            // },
            {
                test: /.tsx?$/,
                use: [
                    { loader: 'cache-loader' },
                    {
                        loader: 'thread-loader',
                        options: {
                            // there should be 1 cpu for the fork-ts-checker-webpack-plugin
                            workers: require('os').cpus().length - 1,
                        },
                    },
                    { loader: 'ts-loader', options: { transpileOnly: true, happyPackMode: true } }
                ]
            },
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader'],
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
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/index.html'
         }),
        new webpack.ProvidePlugin({
            '$': 'jquery',
            'jquery': 'jquery',
            'window.jQuery': 'jquery',
            'jQuery': 'jquery'
        }),
        // new ESBuildPlugin(),
        new ForkTsCheckerWebpackPlugin({ checkSyntacticErrors: true }),
    ],
    // optimization :{
    //   minimize: true,
    //     minimizer: [
    //         new ESBuildMinifyPlugin({target: 'es2015'})
    //     ]
    // },
    devServer: {
        historyApiFallback: true,
        compress: true,
        port: 9000,
        proxy: {
            '/api': {
                target: 'http://localhost:8080/',
                secure: false
            }
        }
    }
}