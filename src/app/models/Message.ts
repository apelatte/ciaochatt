import { User } from "./User";

export class Message{
    id!: number;
    text!: String;
    time!: Date;
    fromID!: number;
    toID!: number;
    chatID!: number;
}