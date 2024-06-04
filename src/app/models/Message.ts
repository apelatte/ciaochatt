import { User } from "./User";

export class Message{
    id!: number;
    text!: String;
    time!: Date;
    from!: User;
    to!: User;
}