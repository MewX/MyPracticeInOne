/**
 * Created by MewX on 4/22/2017.
 */

import {Toolbar, BottomNavigation} from 'react-native-material-ui';
import {ListView, Text, TextInput, TouchableNativeFeedback, View} from "react-native";
import * as React from "react";

class MainActivity extends React.Component {
    constructor() {
        super();
        this.state = {
            active: 'all',
            edit: '',
            items: [
                {text: 'Done sample', done: true},
                {text: 'Pending sample', done: false}
            ]
        }
    }

    render() {
        return (
            <View style={{flex:1}}>
                <Toolbar
                    leftElement="menu"
                    centerElement="Material Todo List"
                />

                <TextInput
                    style={{height: 32, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(text) => this.setState({edit: text})}
                    value={this.state.edit}
                    onSubmitEditing={(event) => this.addNewItem(event.nativeEvent.text)}
                />

                <ListView
                    dataSource={this.state.items}
                    renderRow={(rowData) => {
                        if (rowData.done)
                            return (<TouchableNativeFeedback
                                background={TouchableNativeFeedback.SelectableBackground()}>
                                <View style={{width: 150, height: 100, backgroundColor: 'white'}}>
                                    <Text style={{margin: 30}}>{rowData}</Text>
                                </View>
                            </TouchableNativeFeedback>);
                        else
                            return (<TouchableNativeFeedback
                                background={TouchableNativeFeedback.SelectableBackground()}>
                                <View style={{width: 150, height: 100, backgroundColor: 'grey'}}>
                                    <Text style={{margin: 30}}>{rowData}</Text>
                                </View>
                            </TouchableNativeFeedback>);
                    }
                    }
                />

                <BottomNavigation active={this.state.active} hidden={false}>
                    <BottomNavigation.Action
                        key="all"
                        icon="align-justify"
                        label="all"
                        onPress={() => this.setState({active: 'all'})}/>
                    <BottomNavigation.Action
                        key="pending"
                        icon="hand-pointing-right"
                        label="pending"
                        onPress={() => this.setState({active: 'pending'})}/>
                    <BottomNavigation.Action
                        key="done"
                        icon="archive"
                        label="done"
                        onPress={() => this.setState({active: 'done'})}/>
                </BottomNavigation>
            </View>
        )
    }

    addNewItem(str) {
        let i = this.state.items;
        i.push({text: str, done: false});
        this.setState({items: i});
    }
}

module.exports = MainActivity;